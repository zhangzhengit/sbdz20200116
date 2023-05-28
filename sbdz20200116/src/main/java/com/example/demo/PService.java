package com.example.demo;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import com.example.demo.p.CU;
import com.example.demo.p.G;
import com.example.demo.p.PU;
import com.example.demo.p.PX;
import com.example.demo.p.RR;
import com.example.demo.p.RRPU;
import com.example.demo.p.URE;
import com.example.demo.p.UU;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;

/**
 *
 * 出牌
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
@Service
public class PService {

	private static final Charset FOR_NAME = Charset.forName("UTF-8");

	public SU s() {
		final int[] g = CU.g();
		final List<PU> list = UU.wj(g);
		final List<UR> r = Lists.newArrayListWithCapacity(list.size());
		for (final PU pu : list) {
			final UR build = UR.builder().id(pu.getId())
					.ure(pu.getUre().name())
					.cArray(pu.getCArray())
					.nextId(pu.getNext().getId())
					.previousId(pu.getPrevious().getId())
					.build();
			r.add(build);
		}

		final Optional<UR> findFirst = r.stream().filter(ur -> ur.getUre().equals(URE.L.name())).findFirst();
		final String pId = pId(r);
		final Integer tId = findFirst.get().getId();
		final SU su = new SU(pId, tId, r);
		return su;
	}


	private static String pId(final List<UR> r) {
		final long nanoTime = System.nanoTime();
		final String pId = Hashing.sha512()
						.newHasher()
						.putString(String.valueOf(nanoTime) + r, FOR_NAME)
						.hash().toString();
		return pId;
	}

	public List<RRPU> p_list(final PUR pur) {
		// TODO pId没用到，服务端没存任何信息
		final String pId = pur.getpId();
		final Optional<PU> findAny = getTUID(pur);
		if (!findAny.isPresent()) {
			throw new PException(PEE.NU);
		}

		final PU tpu = findAny.get();
		if (Objects.isNull(tpu.getPrevious().getPrPX()) && Objects.isNull(tpu.getPrevious().getPrevious().getPrPX())) {
			final List<RRPU> plist = RR.pp_p(tpu, pur.getMs());
			return plist;
		}

		PX ppx = tpu.getPrevious().getPrPX();
		if (Objects.isNull(ppx)) {
			ppx = tpu.getPrevious().getPrevious().getPrPX();
		}
		final List<RRPU> gg = RR.gg_p(tpu, ppx, pur.getMs());
		return gg;
	}


	public static Optional<PU> getTUID(final PUR pur) {
		final Integer tId = pur.gettId();
		final List<UR> urlist = gurlist(tId, pur.getUrList());
		final List<PU> pulist = getpu(urlist);
		for (final PU pu2 : pulist) {
			Arrays.sort(pu2.getCArray());
		}
		final Optional<PU> findAny = pulist.stream().filter(pu -> tId.equals(pu.getId())).findAny();
		return findAny;
	}

	public RRPU p(final PUR pur) {
		// TODO pId没用到，服务端没存任何信息
		final String pId = pur.getpId();
		final Optional<PU> findAny = getTUID(pur);
		if (!findAny.isPresent()) {
			throw new PException(PEE.NU);
		}

		final PU tpu = findAny.get();
		if (Objects.isNull(tpu.getPrevious().getPrPX()) && Objects.isNull(tpu.getPrevious().getPrevious().getPrPX())) {
			final RRPU pp = RR.pp(tpu, pur.getMs());
			return pp;
		}

		PX ppx = tpu.getPrevious().getPrPX();
		if (Objects.isNull(ppx)) {
			ppx = tpu.getPrevious().getPrevious().getPrPX();
		}
		final RRPU gg = RR.gg(tpu, ppx, pur.getMs());
		return gg;
	}

	private static List<PU> getpu(final List<UR> urlist) {
		final UR u1 = urlist.get(0);
		final UR u2 = urlist.get(1);
		final UR u3 = urlist.get(2);

		final PU pu1 = PU.builder().id(u1.getId()).ure(URE.valueOf(u1.getUre())).cArray(u1.getcArray()).build();
		final PU pu2 = PU.builder().id(u2.getId()).ure(URE.valueOf(u2.getUre())).cArray(u2.getcArray()).build();
		final PU pu3 = PU.builder().id(u3.getId()).ure(URE.valueOf(u3.getUre())).cArray(u3.getcArray()).build();

		pu1.setPrPX(gpx(u1.getPreviousCArray()).orElse(null));
		pu1.setNext(pu2);
		pu1.setPrevious(pu3);

		pu2.setPrPX(gpx(u2.getPreviousCArray()).orElse(null));
		pu2.setNext(pu3);
		pu2.setPrevious(pu1);

		pu3.setPrPX(gpx(u3.getPreviousCArray()).orElse(null));
		pu3.setNext(pu1);
		pu3.setPrevious(pu2);

		final ArrayList<PU> pulist = Lists.newArrayList(pu1, pu2, pu3);
		return pulist;
	}

	private static Optional<PX> gpx(final int[] array) {
		final boolean empty = ArrayUtils.isEmpty(array);
		if (empty) {
			return Optional.empty();
		}
		return G.f(array);
	}

	private static List<UR> gurlist(final Integer tId, final List<UR> urList) {

		final Optional<UR> findAny = urList.stream().filter(ur -> ur.getId().equals(tId)).findAny();
		if (!findAny.isPresent()) {
			throw new PException(PEE.NU);
		}

		final UR u1 = findAny.get();
		final Integer nextId = u1.getNextId();
		final Optional<UR> findAny2 = urList.stream().filter(ur -> ur.getId().equals(nextId)).findAny();
		if (!findAny2.isPresent()) {
			throw new PException(PEE.NU);
		}

		final UR u2 = findAny2.get();
		final Integer nextId2 = u2.getNextId();

		final Optional<UR> findAny3 = urList.stream().filter(ur -> ur.getId().equals(nextId2)).findAny();
		if (!findAny3.isPresent()) {
			throw new PException(PEE.NU);
		}

		final UR u3 = findAny3.get();
		final ArrayList<UR> r = Lists.newArrayList(u1, u2, u3);
		return r;
	}

}
