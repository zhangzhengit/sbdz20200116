package com.example.demo;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.p.CU;
import com.example.demo.p.G;
import com.example.demo.p.GU;
import com.example.demo.p.PU;
import com.example.demo.p.PX;
import com.example.demo.p.RR;
import com.example.demo.p.RRPU;
import com.example.demo.p.UU;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 出牌接口
 *
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
// TODO 改用websocket实时返回结果
@Slf4j
@RestController
public class PController {

	@Autowired
	private PService pService;

	@Autowired
	private MachineConfiguration machineConfiguration;

	private final ExecutorService service = Executors.newCachedThreadPool();

	/**
	 * 判定一组牌是否符合牌型规则
	 *
	 * @param fp
	 * @return 返回true或false
	 */
	@PostMapping("/f")
	public R<Boolean> f(@RequestBody final FP fp) {
		log.info("fp={}", fp);
		final int[] array = fp.getArray();
		Arrays.sort(array);
		final Optional<PX> f = G.f(array);
		final R<Boolean> ok = R.ok(f.isPresent());
		return ok;
	}

	/**
	 * 判断一组牌能否要得起另一组牌
	 *
	 * @param y
	 * @return
	 */
	@PostMapping("/y")
	public R<Boolean> y(@RequestBody final Y y) {
		log.info("y={}", y);

		final int[] pa = y.getArray1();
		final Optional<PX> f = G.f(pa);
		final PX px = f.get();
		final int[] a2 = y.getArray2();
		Arrays.sort(a2);
		final List<PX> ydq = GU.ydq(px, a2);
		return R.ok(!ydq.isEmpty());
	}

	/**
	 * 初始化三个人的牌，只有牌
	 *
	 * @param s
	 * @return
	 */
	@GetMapping("/s1/{s}")
	public String s1(@PathVariable final Integer s) {
		final int[] g = CU.g();
		final List<PU> wj = UU.wj(g);
		if (Objects.nonNull(s) && s > 0 && s <= 17) {
			for (final PU pu : wj) {
				ArrayUtils.shuffle(pu.getCArray());
				final int[] c = Arrays.copyOfRange(pu.getCArray(), 0, s);
				Arrays.sort(c);
				pu.setCArray(c);
			}
		}
		final StringJoiner joiner = new StringJoiner("\n");
		for (final PU pu : wj) {
			joiner.add(Arrays.toString(pu.getCArray()));
		}
		return joiner.toString();
	}

	/**
	 * 初始化牌局信息
	 *
	 * @return
	 */
	@GetMapping("/s")
	public R<SU> s() {
		final SU s = pService.s();
		log.info("s={}", s);
		return R.ok(s);
	}

	@PostMapping("/p")
	public R<RRPU> p(@RequestBody @Validated final PUR pur, final BindingResult bindingResult) {
//		log.info("pur={}", pur);
		final RRPU p = pService.p(pur);
//		log.info("RRPU={}", p);
		return R.ok(p);
	}

	/**
	 * 供外部调用的接口
	 *
	 * @param pur
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/p0")
	// public R<RRPU> p0(@RequestBody @Validated final PUR pur, final
	// BindingResult bindingResult) {
	public R<RRPU> p0(@RequestBody final PUR pur) {
		log.info("pur={}", pur);
		final Set<String> urlSet = machineConfiguration.getUrl();
		final Integer ms = pur.getMs();
		final int msj = 100;
		final int rms = ms < msj ? ms : ms - msj + 50;
		pur.setMs(rms);
		final Optional<PU> tpuO = pService.getTUID(pur);
		final PU tpu = tpuO.get();
		final boolean g = Objects.nonNull(tpu.getPrevious().getPrPX()) || Objects.nonNull(tpu.getPrevious().getPrevious().getPrPX());

		final CountDownLatch latch = new CountDownLatch(urlSet.size());
		// 总结果
		final CopyOnWriteArrayList<RRPU> r = Lists.newCopyOnWriteArrayList();

		// 调用所有配置的接口
		for (final String url : urlSet) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					final List<RRPU> post = post(url, pur);
					r.addAll(post);
					latch.countDown();
				}
			});
		}
		try {
			latch.await();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}

		Map<PX, List<RRPU>> map = null;
		if (!g) {
			map = r.parallelStream().collect(Collectors.groupingBy(RRPU::getPx, Collectors.toList()));
		} else {
			final Map<PX, List<RRPU>> genmap = r.parallelStream().filter(rrpu -> rrpu.getPx() != null)
					.collect(Collectors.groupingBy(RRPU::getPx, Collectors.toList()));
			final List<RRPU> bugenlist = r.parallelStream().filter(rrpu -> rrpu.getPx() == null).collect(Collectors.toList());
			// 跟牌考虑不跟（不出）的情况
			genmap.put(null, bugenlist);
			map = genmap;
		}

		final List<RRPU> rrrlist = map.entrySet().stream().map(en -> {
			long gc = 0L;
			long wc = 0L;
			final List<RRPU> value = en.getValue();
			for (final RRPU rx : value) {
				gc += rx.getGc();
				wc += rx.getWc();
			}
			return new RRPU(tpu.getId(), tpu.getUre(), en.getKey(), gc, wc);
		}).collect(Collectors.toList());

		System.out.println("@=========================================================");
		final RRPU maxwrpx = g ? RR.maxGGwrpx(tpu, rrrlist) : RR.maxPPwrpx(tpu, rrrlist);

		rrrlist.sort(Comparator.comparing(RRPU::getWR));
		System.out.println("所有牌型总数=" + rrrlist.size());
		long sum = 0L;
		for (final RRPU rrpu : rrrlist) {
			System.out.println("\t" + rrpu);
			sum += rrpu.getGc();
		}
		System.out.println("所有牌型总数=" + rrrlist.size());
		System.out.println("最大胜率出法=" + maxwrpx);
		System.out.println("所有牌型总局数=" + sum);
		System.out.println("@=========================================================");
		return R.ok(maxwrpx);
	}

	/**
	 * 实际的出牌接口
	 *
	 * @param pur
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/p_")
	public Object p_(@RequestBody final PUR pur, final BindingResult bindingResult) {
		System.out.println(Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "PController.p_()");
		System.out.println();

		log.info("pur={}", pur);
		final List<RRPU> p_list = pService.p_list(pur);
		log.info("p_list={}", p_list);
		return p_list;
	}

	/**
	 * post请求
	 *
	 * @param url
	 * @param request
	 * @return
	 */
	List<RRPU> post(final String url, final Object request) {
		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, request, String.class);
		final String body = postForEntity.getBody();
		final List<RRPU> parseObject = JSON.parseObject(body, new TypeReference<List<RRPU>>() {
		});

		return parseObject;

	}

}
