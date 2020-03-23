#!/usr/bin/env bash

sbt -Dbrowser=remote-chrome -Dchrome-headless=true -Denvironment=local  'testOnly ctc.suites.Runner'
