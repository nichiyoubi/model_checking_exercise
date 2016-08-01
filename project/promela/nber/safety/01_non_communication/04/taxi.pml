/*
 タクシー2台のモデル。
 
 タクシー1は、posA -> posB -> posC -> posA -> ... という順番で循環する
 タクシー2は、posD -> posE -> posB -> posD -> ... という順番で循環する

 posBを共有する道路ですれ違いはできない。
 
 タクシー1もタクシー2も他車との距離が2より多くなるまで待つ。
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

int d = 10;

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
            :: else -> assert false
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
    :: d <= 2 -> /* タクシー2と同様、Bに入る前に超音波センサの値を見て、タクシー2がAまたはBにいなかったらBに入る */
        (d > 2); goto B;
    :: else -> goto B;
    fi;

B:
    atomic {
        pos1 = posB; update_d();
    }
    if
    :: d == 0 -> 
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
    :: d <= 2 -> /* Bに入る前に超音波センサの値を見て、タクシー1がAまたはBにいなかったらBに入る */
        (d > 2); goto B;
    :: else -> goto B;
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
