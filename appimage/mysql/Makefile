default: all

DIR=${CURDIR}


debug:
	echo $(DIR)

clean:
	rm -rf target/
	mkdir target/



build: clean
	chmod +x ../appimagetool-x86_64.AppImage
	../appimagetool-x86_64.AppImage src/main/MySqlDump.AppDir target/MySqlDump.AppImage
	chmod +x target/MySqlDump.AppImage

build_test: build
	cp src/test/test.sh target/
	chmod +x target/test.sh


test: build_test
    #mount target dir to docker container for runnning tests
	docker run --rm -v $(DIR)/target/:/target   debian  /target/test.sh
	docker run --rm -v $(DIR)/target/:/target   ubuntu  /target/test.sh
	docker run --rm -v $(DIR)/target/:/target   centos  /target/test.sh


all: test