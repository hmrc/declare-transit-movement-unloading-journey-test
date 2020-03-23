#!/usr/bin/env bash
sbt -Dbrowser=chrome -Dbrowsermode=headless -Denvironment=local 'testOnly ctc.suites.Runner'
