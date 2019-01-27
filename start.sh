#!/bin/bash

sudo javac -cp .:mysql-connector-java-5.1.47-bin.jar app/*.java beans/*.java controller/*.java cresponse/*.java cstate/*.java dao/*.java daofactory/*.java jdbc/*.java utils/*.java view/*.java view/customer/*.java view/seller/*.java view/admin/*.java
sudo java -cp .:mysql-connector-java-5.1.47-bin.jar app.Main