# DevOps Demo — Task Management REST API

A Spring Boot application used to demonstrate a full DevOps pipeline:
CI/CD with Jenkins, configuration management with Ansible, and
container orchestration with Kubernetes.

**Author:** Ashmit Kumar Verma (1MS24CI027)
**Course:** DevOps (24CIAEC59) — M. S. Ramaiah Institute of Technology

## Running Locally
```
./mvnw clean package
java -jar target/devops-demo-1.0.0.jar
```
API available at http://localhost:8080/api/tasks

## Project Structure
```
devops-project/
├── src/                     # Spring Boot application source
├── pom.xml                  # Maven build configuration
├── Jenkinsfile              # Declarative CI/CD pipeline
├── Dockerfile               # Multi-stage container build
├── .github/workflows/       # GitHub Actions CI
├── ansible/                 # Ansible role and playbook
│   ├── inventory.ini
│   ├── playbook.yml
│   └── roles/webapp/
├── k8s/                     # Kubernetes manifests
│   ├── configmap.yaml
│   ├── deployment.yaml
│   └── service.yaml
└── scripts/                 # Deployment and demo scripts
```

## Git Workflow
- `main` — stable branch, only merge commits
- `feature/task-api` — REST API implementation (PR #1)
- `feature/ci-pipeline` — Jenkins + GitHub Actions (PR #2)
- `feature/ansible-config` — Ansible configuration (PR #3)
- `feature/k8s-deployment` — Containerization and K8s (PR #4)
