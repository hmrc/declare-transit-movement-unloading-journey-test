#!/usr/bin/env bash
sbt -Dbrowser=remote-chrome -Da11y=true -Denvironment=local 'testOnly ctc.suites.Runner'
