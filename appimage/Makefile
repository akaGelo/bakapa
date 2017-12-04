default: all
# postgresql

SUBDIRS = mysql
all:
	 for dir in $(SUBDIRS); do \
	   $(MAKE) -C $$dir  clean all;\
	 done
