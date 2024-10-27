#!/bin/bash
set -x
set -e

echo "JVM HEAP MIN: $MIN"
echo "JVM HEAP MAX: $MAX"
echo "ENVIRONMENT: $ENVIRONMENT"

VM_ARGS="$MIN $MAX -Dmanagement.endpoint.health.show-details=always "

echo "VM ARGS - $VM_ARGS"

java $VM_ARGS -jar "/opt/app/MyShareIt.jar"
