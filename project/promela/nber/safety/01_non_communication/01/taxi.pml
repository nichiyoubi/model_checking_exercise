/*
 タクシー2台のモデル。
 
 タクシー1は、posA -> posB -> posC -> posA -> ... という順番で循環する
 タクシー2は、posD -> posE -> posB -> posD -> ... という順番で循環する

 posBを共有する道路ですれ違いはできない。

 お互い何も強調しない。互いの距離が0になったら衝突回避のため止まる。距離が0でなくなるまで待つ。
 */

mtype = { posA, posB, posC, posD, posE };

mtype pos1 = posA;
mtype pos2 = posD;

/*
  d posA posE = 2
  d posA posD = infinity
  d posA posB = 1
  d posB posE = 1
  d posB posD = infinity
  d posB posB = 0
  d posC posE = infinity
  d posC posD = infinity
  d posC posB = infinity
 */

int d = 10; /* inifinityを10で表現 */

inline update_d() {
    atomic {
        if
        :: pos1 == posC -> d = 10
        :: pos2 == posD -> d = 10
        :: else ->
            if
            :: pos1 == posA && pos2 == posE -> d = 2
            :: pos1 == posA && pos2 == posB -> d = 1
            :: pos1 == posB && pos2 == posE -> d = 1
            :: pos1 == posB && pos2 == posB -> d = 0
            :: else -> assert(false)
            fi
        fi
    }
}

active proctype taxi1() {
A:
    atomic {
        pos1 = posA; update_d();
    }
    if
    :: goto B;
    fi;

B:
    atomic {
        pos1 = posB; update_d();
    }
    if
    :: d == 0 -> /* 超音波センサで計測した距離が0のときは、0でなくなるまで待つ */
        (d != 0); goto C;
    :: else -> goto C;
    fi;

C:
    atomic {
        pos1 = posC; update_d();
    }
    if
    :: goto A;
    fi
}

active proctype taxi2() {
D:
    atomic {
        pos2 = posD; update_d();
    }
    if
    :: goto E;
    fi;

E:
    atomic {
        pos2 = posE; update_d();
    }
    if
    :: goto B;
    fi

B:
    atomic {
        pos2 = posB; update_d();
    }
    if
    :: d == 0 -> 
        (d != 0); goto D;
    :: else -> goto D;
    fi
}
