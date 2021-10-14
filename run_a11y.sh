#!/usr/bin/env bash
sbt -Dbrowser=chrome -Da11y=true -Denvironment=local 'testOnly ctc.suites.Runner_A11y'
