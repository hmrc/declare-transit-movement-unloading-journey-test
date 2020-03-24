#!/bin/bash

sbt -Dbrowser=remote-chrome -Dlogback.configurationFile=logback.xml -Dzap.proxy=true -Dcucumber.options="--tags @unloading_remarks" 'testOnly ctc.suites.ZapSuite'
sbt 'testOnly ctc.suites.ZAPRunner'
