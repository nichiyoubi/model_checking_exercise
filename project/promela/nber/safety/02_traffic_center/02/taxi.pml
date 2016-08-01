/*
 タクシー2台のモデル。
 
 タクシー1は、posA -> posB -> posC -> posA -> ... という順番で循環する
 タクシー2は、posD -> posE -> posB -> posD -> ... という順番で循環する

 posBを共有する道路ですれ違いはできない。

 先ほどと同じ方式。超音波センサによる計測をやめたモデル
 */

#define N 2

mtype = { posA, posB, posC, posD, posE };
mtype = { aquire, release, ack, nack }

chan cch = [N] of { mtype, mtype };
chan tch[2] = [0] of { mtype };

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
            fi
        fi
    }
}

active proctype taxi1() {
A:
    atomic {
        pos1 = posA; update_d();
    }
A_again:
    cch!_pid, aquire; /* センターに通行許可を要求 */
    if
    :: tch[_pid]?ack -> goto B;  /* 許可が与えられればBに入る */
    :: tch[_pid]?nack -> goto A_again; /* 許可が得られない場合はもう一度要求を出す */
    fi;

B:
    atomic {
        pos1 = posB; update_d();
    }
    if
    :: assert(d != 0); goto C;
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
E_again:
    cch!_pid, aquire; /* タクシー1と同様 */
    if
    :: tch[_pid]?ack -> goto B;
    :: tch[_pid]?nack -> goto E_again;
    fi;

B:
    atomic {
        pos2 = posB; update_d();
    }
    if
    :: assert(d != 0); goto D;
    fi
}

active proctype center()
{
    int count = 0;
    int n;
    do
    :: cch?n, aquire ->
       if
       :: count == 0 -> count++; tch[n]!ack
       :: else -> tch[n]!nack
       fi
    :: cch?n, release ->
       assert(count == 1);
       count--;
    od
}