#!/usr/bin/env bash
{
  sleep 1
  echo "use declare-transit-movement-unloading-frontend"
  echo "db['user-answers'].drop()"
} | mongo
