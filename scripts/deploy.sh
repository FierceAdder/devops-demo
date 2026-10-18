#!/usr/bin/env bash
set -euo pipefail

echo "=== Building Docker image ==="
docker build -t devops-demo:1.0 .

echo ""
echo "=== Loading image into Minikube ==="
minikube image load devops-demo:1.0

echo ""
echo "=== Applying Kubernetes manifests ==="
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

echo ""
echo "=== Waiting for rollout ==="
kubectl rollout status deployment/devops-demo

echo ""
kubectl get pods -l app=devops-demo
kubectl get svc devops-demo-service

echo ""
echo "=== App URL: http://\$(minikube ip):30080/api/tasks ==="
