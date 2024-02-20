CC = gcc
CFLAGS = -Wall -fPIC
LDFLAGS = -Wall -L. -lTwisk
RES = ../ressource/codeC/programmeC.o
all: main

main: main.c libTwisk.so
	$(CC) $(CFLAGS) $< -o $@ $(LDFLAGS)

libTwisk.so: $(RES) client.o
	$(CC) -shared $^ -o $@

client.o: client.c
	$(CC) $(CFLAGS) -c $< -o $@
	
.PHONY: all clean

clean:
	rm -f main.o main libTwisk.so client.o


