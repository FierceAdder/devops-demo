#!/usr/bin/env bash
set -euo pipefail
DEPLOY="devops-demo"
NS="default"

echo "=== Current pods ==="
kubectl get pods -l app=$DEPLOY -n $NS

echo ""
echo "=== Scaling UP to 5 replicas ==="
kubectl scale deployment $DEPLOY --replicas=5 -n $NS
kubectl rollout status deployment/$DEPLOY -n $NS
kubectl get pods -l app=$DEPLOY -n $NS

echo ""
echo "=== Scaling DOWN to 3 replicas ==="
kubectl scale deployment $DEPLOY --replicas=3 -n $NS
kubectl rollout status deployment/$DEPLOY -n $NS
kubectl get pods -l app=$DEPLOY -n $NS

echo ""
echo "=== Service info ==="
kubectl get svc devops-demo-service -n $NS
