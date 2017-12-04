#!/bin/sh
cd /target/


/target/MySqlDump.AppImage --appimage-extract
/target/squashfs-root/AppRun --version

EXIT_CODE=$?

rm  -rf   /target/squashfs-root/

exit $EXIT_CODE