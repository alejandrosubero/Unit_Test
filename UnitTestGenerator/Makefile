.PHONY: all build install uninstall clean dist

all: build

build:
	mvn clean package
	mkdir -p cli
	cp target/unit-*.jar cli/
	chmod +x cli/unit
	unix2dos cli/unit.bat

dist: build
	zip -r unit-test-dist.zip cli/ Makefile

install:
	./cli/unit install

uninstall:
	./cli/unit uninstall

clean:
	mvn clean
	rm -rf cli unit-test-dist.zip