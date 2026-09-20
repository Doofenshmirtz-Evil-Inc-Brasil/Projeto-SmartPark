#!/usr/bin/env bash
set -e
rm -rf out
mkdir out
javac -d out $(buscar src/main/java -name "*.java")
java -cp out br.edu.smartpark.Main
