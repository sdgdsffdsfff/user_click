#!/bin/bash

echo $1
ANT_BUILD=/usr/local/ant/bin/ant
echo $ANT_BUILD
OPT=1
if [ "$1" = "user_click" ]
then 
 echo "build user_click";
 $ANT_BUILD -buildfile build.xml
else
 OPT=1
fi

if [[ "$1" = "tool" ]]
then
 rm -rf tool_src/cn
 cd src
 cp -r --parents cn/clickwise/lib ../tool_src
 cd ..
 echo "build tool";
 $ANT_BUILD -buildfile build_tool.xml
 rm cassandra_lib/mytool.jar
 cp out/mytool.jar cassandra_lib
 rm rpc_lib/mytool.jar
 cp out/mytool.jar rpc_lib
else
 OPT=2
fi

if [ $1 = "feathouse" ]
then
 rm -rf feathouse_src/cn
 cd src 
 cp -r --parents cn/clickwise/clickad/feathouse/ ../feathouse_src
 cd ..
 echo "build feathouse";
 $ANT_BUILD -buildfile build_feathouse.xml
else
 OPT=3
fi

if [ $1 = "rpc" ]
then
 rm -rf rpc_src/cn
 cd src
 cp -r --parents cn/clickwise/rpc ../rpc_src
 cd ..
 echo "build rpc";
 $ANT_BUILD -buildfile build_rpc.xml
 rm cassandra_lib/rpc.jar
 cp out/rpc.jar cassandra_lib
else
 OPT=3
fi

