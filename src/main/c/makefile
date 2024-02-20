CC = gcc
CFLAGS = -Wall -fPIC
LDFLAGS = -shared
C_SRC = main.c
CLIENT_SRC = client.c
OBJ = main.o client.o
LIB = libTwisk.so

all: $(LIB)

$(LIB): $(OBJ)
	$(CC) $(LDFLAGS) $(OBJ) -o $(LIB)

main.o: $(C_SRC)
	$(CC) $(CFLAGS) -c $(C_SRC) -o main.o

client.o: $(CLIENT_SRC)
	$(CC) $(CFLAGS) -c $(CLIENT_SRC) -o client.o

clean:
	rm -f $(OBJ) $(LIB)

