#!/usr/bin/env bash
sbt -Dbrowser=chrome -Denvironment=local 'testOnly ctc.suites.Runner' testReport
