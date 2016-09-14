all:
	spin -a taxi.pml
	gcc -o pan -DREACH pan.c
	./pan -i

show:
	spin -t -l -p taxi.pml

clean:
	rm -f *.trail pan*

