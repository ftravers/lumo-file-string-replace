#!/bin/sh

set -e

lumo -K -c src -m "dyndns.core" $@
