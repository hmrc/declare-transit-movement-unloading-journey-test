#!/bin/bash

sbt -Dbrowser=remote-chrome -Dlogback.configurationFile=logback.xml -Dzap.proxy=true -Dcucumber.options="--tags @arrival_submission" 'testOnly ctc.suites.ZapSuite'
sbt 'testOnly ctc.suites.ZAPRunner'
