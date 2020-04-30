#!/usr/bin/env bash
sbt -jvm-debug 5005 -Dbrowser=chrome -Denvironment=local 'testOnly ctc.suites.Runner_WIP'
