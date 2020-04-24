#!/usr/bin/env bash
./drop_unloading_frontend_data.sh
sbt -Dbrowser=chrome -Denvironment=local 'testOnly ctc.suites.Runner'
