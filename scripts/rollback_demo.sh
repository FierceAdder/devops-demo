#!/usr/bin/env bash
set -euo pipefail

DEPLOY="devops-demo"
NS="default"

echo "=== Current revision ==="
kubectl rollout history deployment/$DEPLOY -n $NS

echo ""
echo "=== Rolling back to previous revision ==="
kubectl rollout undo deployment/$DEPLOY -n $NS
kubectl rollout status deployment/$DEPLOY -n $NS

echo ""
echo "=== Pods after rollback ==="
kubectl get pods -l app=$DEPLOY -n $NS
