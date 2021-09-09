#!/bin/bash

sbt -Dbrowser=remote-chrome -Dlogback.configurationFile=logback.xml -Dzap.proxy=true 'testOnly ctc.suites.Runner'
