#!/usr/bin/env bash
./drop_unloading_frontend_data.sh
sbt -Dbrowser=firefox -Denvironment=local 'testOnly ctc.suites.Runner'
