#!/usr/bin/env bash

sbt -Dbrowser=chrome -Dchrome-headless=true -Denvironment=local  'testOnly ctc.suites.Runner'
