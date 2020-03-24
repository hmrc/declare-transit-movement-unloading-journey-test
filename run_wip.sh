
#!/usr/bin/env bash
sbt -Dbrowser=firefox -Denvironment=local 'testOnly ctc.suites.Runner_WIP'
