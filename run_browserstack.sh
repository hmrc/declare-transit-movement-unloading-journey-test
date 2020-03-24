#!/bin/bash
username=""
key=""

sbt -Dbrowser=browserstack \
-Dbrowserstack.username=${username} \
-Dbrowserstack.key=${key} \
-Dbrowserstack.project="CTC Traders" \
-Dbrowserstack.browser_version="69" \
-Dbrowserstack.browser="Firefox" \
-Dbrowserstack.os="OS X" \
-Dbrowserstack.os_version="Mojave" \
'testOnly ctc.suites.Runner'
