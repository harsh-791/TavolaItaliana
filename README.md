# Chef and Dish Management System

A production-grade Spring Boot application for managing chefs and their dishes, featuring a comprehensive **DevSecOps CI/CD pipeline** that enforces security, quality, and reliability at every stage.

## 🎯 Project Overview

This project demonstrates **production-grade DevOps practices** with:

- **Shift-left security** - Security checks integrated early in the development lifecycle
- **Quality gates** - Automated code quality and testing enforcement
- **Container security** - Multi-layered container scanning and validation
- **Fail-fast behavior** - Pipeline stops immediately on any security or quality issue
- **Production thinking** - Every stage designed to prevent vulnerable code from reaching production

---

## 🏗️ Technical Stack

### Backend
- **Spring Boot 3.4.5** - Modern Java framework
- **Spring Data JPA** - Database abstraction layer
- **Spring MVC** - Web framework with JSP views
- **MySQL 8.0** - Relational database

### Frontend
- **JSP (JavaServer Pages)** - Server-side rendering
- **Bootstrap 5** - Responsive UI framework
- **JSTL** - JSP Standard Tag Library

### Testing & Quality
- **JUnit 5** - Unit testing framework
- **Mockito** - Mocking framework
- **Checkstyle** - Code quality enforcement
- **OWASP Dependency-Check** - Dependency vulnerability scanning

### DevOps & Security
- **GitHub Actions** - CI/CD automation
- **CodeQL** - Static Application Security Testing (SAST)
- **Trivy** - Container image security scanning
- **Docker** - Containerization

---

## 🚀 Quick Start

### Prerequisites

- **Java 17** or higher
- **MySQL 8.0** or higher
- **Maven 3.6** or higher
- **Docker** (for containerized deployment)

### Local Development Setup

1. **Clone the repository:**
   ```bash
   git clone [repository-url]
   cd chefapp
   ```

2. **Set up MySQL database:**
   ```sql
   CREATE DATABASE chefapp;
   CREATE USER 'chefapp_user'@'localhost' IDENTIFIED BY 'chefapp_password';
   GRANT ALL PRIVILEGES ON chefapp.* TO 'chefapp_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the application:**
   - Open browser: `http://localhost:8080`
   - The application redirects to `/chefs` by default

### Running with Docker

1. **Build the Docker image:**
   ```bash
   docker build -t chefapp:latest .
   ```

2. **Run the container:**
   ```bash
   docker run -d \
     --name chefapp \
     -p 8080:8080 \
     -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/chefapp \
     -e SPRING_DATASOURCE_USERNAME=chefapp_user \
     -e SPRING_DATASOURCE_PASSWORD=chefapp_password \
     chefapp:latest
   ```

---

## 🔐 CI/CD Pipeline Architecture

This project implements a **production-grade DevSecOps CI pipeline** that runs automatically on every push to `main` and can be manually triggered via `workflow_dispatch`.

### Pipeline Flow Diagram

```
┌─────────────┐
│   Checkout  │ ← Retrieve source code
└──────┬──────┘
       │
┌──────▼──────┐
│   Setup     │ ← Configure Java/Maven environment
└──────┬──────┘
       │
┌──────▼──────┐
│  Linting    │ ← Code quality checks (Checkstyle)
└──────┬──────┘
       │
┌──────▼──────┐
│    SAST     │ ← Static security analysis (CodeQL)
└──────┬──────┘
       │
┌──────▼──────┐
│    SCA      │ ← Dependency vulnerability scan (OWASP)
└──────┬──────┘
       │
┌──────▼──────┐
│ Unit Tests  │ ← Verify application logic
└──────┬──────┘
       │
┌──────▼──────┐
│   Build     │ ← Compile and package application
└──────┬──────┘
       │
┌──────▼──────┐
│ Docker Build│ ← Create containerized image
└──────┬──────┘
       │
┌──────▼──────┐
│ Image Scan  │ ← Container security scan (Trivy)
└──────┬──────┘
       │
┌──────▼──────┐
│ Smoke Test  │ ← Runtime container validation
└──────┬──────┘
       │
┌──────▼──────┐
│ Docker Push │ ← Push trusted image to DockerHub
└─────────────┘
```

---

## 📋 Pipeline Stages - Detailed Explanation

### Stage 1: Checkout
**Purpose:** Retrieve source code from the repository  
**Risk Mitigation:** Ensures we're working with the correct codebase version  
**Failure Impact:** Pipeline cannot proceed without source code  
**Order Rationale:** Must be first - all subsequent stages depend on source code  
**Implementation:** Uses `actions/checkout@v4` with full history for CodeQL analysis

---

### Stage 2: Setup Runtime
**Purpose:** Configure Java 17 and Maven 3.9 environment  
**Risk Mitigation:** Ensures consistent build environment across all pipeline runs  
**Failure Impact:** Build tools unavailable, compilation fails  
**Order Rationale:** Required before any compilation, testing, or analysis  
**Optimization:** Caches Maven dependencies (`~/.m2`) to speed up subsequent builds

---

### Stage 3: Linting (Code Quality)
**Purpose:** Enforce coding standards and catch style violations early  
**Risk Mitigation:** 
- Prevents technical debt accumulation
- Improves code maintainability
- Catches security-relevant code smells (e.g., hardcoded secrets, insecure patterns)
- Ensures consistent code style across the team

**Failure Impact:** 
- Code quality degrades over time
- Harder to maintain and review
- Potential bugs from style violations
- Security issues from poor coding practices

**Order Rationale:** 
- Fast feedback before expensive operations (build/test)
- Early detection of issues saves time
- Prevents bad code from entering the codebase

**Tool:** Checkstyle with production-grade configuration (`checkstyle.xml`)  
**Configuration:** Enforces naming conventions, code complexity limits, whitespace rules, and security best practices

---

### Stage 4: SAST (Static Application Security Testing)
**Purpose:** Detect security vulnerabilities in source code  
**Risk Mitigation:** 
- Finds SQL injection vulnerabilities
- Detects Cross-Site Scripting (XSS) risks
- Identifies insecure deserialization
- Catches hardcoded credentials
- Detects insecure cryptographic usage
- Finds path traversal vulnerabilities

**Failure Impact:** 
- Vulnerable code could be deployed to production
- Security breaches and data leaks
- Compliance violations
- Reputation damage

**Order Rationale:** 
- Early detection (shift-left security principle)
- Before build/test cycles to fail fast
- Catches issues when they're cheapest to fix

**Tool:** GitHub CodeQL with security queries enabled  
**Integration:** Results appear in GitHub Security tab automatically  
**Failure Behavior:** Pipeline fails on security findings

---

### Stage 5: SCA (Software Composition Analysis)
**Purpose:** Scan dependencies for known vulnerabilities (CVEs)  
**Risk Mitigation:** 
- Identifies vulnerable third-party libraries
- Detects outdated dependencies with security flaws
- Prevents supply chain attacks
- Ensures dependency hygiene

**Failure Impact:** 
- Application vulnerable to known exploits via dependencies
- Most vulnerabilities come from dependencies (OWASP Top 10)
- Attackers can exploit known CVEs in dependencies

**Order Rationale:** 
- After SAST, before build (fail fast on vulnerable dependencies)
- Dependencies are the #1 source of vulnerabilities
- Must pass before building the application

**Tool:** OWASP Dependency-Check Maven Plugin  
**Configuration:** 
- Fails build on CVSS score ≥ 7.0 (high/critical)
- Scans all dependencies including transitive ones
- Generates HTML and JSON reports
- Supports suppressions for false positives

**Why This Matters:** 
- According to OWASP, 84% of security vulnerabilities come from dependencies
- Log4Shell, Spring4Shell, and other major incidents were dependency vulnerabilities
- This stage prevents deploying applications with known vulnerable libraries

---

### Stage 6: Unit Tests
**Purpose:** Verify application logic correctness  
**Risk Mitigation:** 
- Catches regressions before deployment
- Validates business logic
- Ensures code changes don't break existing functionality

**Failure Impact:** 
- Broken functionality reaches production
- User-facing bugs
- Data integrity issues

**Order Rationale:** 
- After security scans, before packaging
- Fail fast on test failures
- Quality gate: tests must pass for pipeline to continue

**Tool:** JUnit 5 with Maven Surefire  
**Coverage:** All unit tests in `src/test/java` are executed  
**Reports:** Test results uploaded as artifacts for analysis

---

### Stage 7: Build / Package
**Purpose:** Compile and package application into deployable artifact  
**Risk Mitigation:** 
- Ensures application builds successfully
- Validates compilation errors
- Creates production-ready JAR file

**Failure Impact:** 
- Cannot deploy if build fails
- Compilation errors prevent deployment

**Order Rationale:** 
- After tests pass, before containerization
- Must have a valid artifact before creating container

**Output:** `target/chefapp-0.0.1-SNAPSHOT.jar`  
**Optimization:** Tests skipped here (already ran in Stage 6)

---

### Stage 8: Docker Build
**Purpose:** Create containerized application image  
**Risk Mitigation:** 
- Ensures Dockerfile is valid
- Validates multi-stage build process
- Creates production-ready container image

**Failure Impact:** 
- Cannot deploy containerized application
- Dockerfile errors prevent deployment

**Order Rationale:** 
- After successful build, before image scanning
- Must have a valid image before security scanning

**Security Features:**
- Multi-stage build reduces final image size
- Non-root user execution
- Minimal base image (eclipse-temurin:17-jre-jammy)
- Health check configured
- Security updates applied

**Optimization:** 
- Uses Docker BuildKit cache (`cache-from`/`cache-to`)
- Leverages GitHub Actions cache for faster builds

---

### Stage 9: Image Scan (Container Security)
**Purpose:** Scan container image for vulnerabilities and misconfigurations  
**Risk Mitigation:** 
- Detects vulnerabilities in base image
- Finds vulnerabilities in installed packages
- Identifies misconfigurations (e.g., running as root)
- Detects secrets in image layers
- Validates security best practices

**Failure Impact:** 
- Vulnerable container deployed to production
- Attackers can exploit container vulnerabilities
- Compliance violations

**Order Rationale:** 
- After image build, before push (fail fast)
- Must scan before pushing to registry
- Prevents vulnerable images from being published

**Tool:** Trivy by Aqua Security  
**Scans Performed:**
- **Vulnerability Scan:** CVEs in base image and dependencies
- **Config Scan:** Security misconfigurations
- **Secret Scan:** Hardcoded secrets in image layers

**Failure Behavior:** 
- Pipeline fails on CRITICAL or HIGH severity vulnerabilities
- Results uploaded to GitHub Security tab (SARIF format)
- Detailed reports available as artifacts

**Why This Matters:**
- Container images often contain vulnerable base images
- Even if application code is secure, base image vulnerabilities can be exploited
- This is the final security gate before deployment

---

### Stage 10: Runtime Container Test (Smoke Test)
**Purpose:** Verify container runs correctly and application is accessible  
**Risk Mitigation:** 
- Catches runtime issues before deployment
- Validates container health checks
- Ensures application responds to requests
- Verifies container configuration

**Failure Impact:** 
- Container may not work in production environment
- Application may not start correctly
- Health checks may fail

**Order Rationale:** 
- After image scan passes, before push (fail fast)
- Quality gate: ensures containerized app actually works
- Prevents broken containers from being pushed

**Test Process:**
1. Start container in background
2. Wait for health check to pass
3. Verify application responds to HTTP requests
4. Check logs for errors
5. Clean up test container

**Validation:**
- Container starts successfully
- Health check endpoint responds
- Application endpoint returns valid HTTP response (200/302)
- No critical errors in logs

---

### Stage 11: Docker Push to DockerHub
**Purpose:** Push trusted, scanned image to container registry  
**Risk Mitigation:** 
- Only pushes images that passed all security checks
- Ensures only validated images are available for deployment
- Maintains image provenance

**Failure Impact:** 
- Cannot deploy to production
- Image not available for deployment

**Order Rationale:** 
- Final step - only reached if all quality gates pass
- All security and quality checks must pass first

**Security:**
- Uses GitHub Secrets for authentication (`DOCKERHUB_USERNAME`, `DOCKERHUB_TOKEN`)
- No hardcoded credentials
- Pushes both commit SHA tag and `latest` tag
- Images are immutable (SHA-based tags)

**Tags Created:**
- `{username}/chefapp:{commit-sha}` - Immutable, traceable to commit
- `{username}/chefapp:latest` - Latest successful build

---

## 🔑 GitHub Secrets Configuration

The pipeline requires the following secrets to be configured in GitHub:

### Required Secrets

1. **`DOCKERHUB_USERNAME`**
   - Your Docker Hub username
   - Used for authenticating to Docker Hub

2. **`DOCKERHUB_TOKEN`**
   - Your Docker Hub access token (NOT password)
   - Create at: https://hub.docker.com/settings/security
   - Must have write permissions

### Setting Up Secrets

1. Go to your GitHub repository
2. Navigate to **Settings** → **Secrets and variables** → **Actions**
3. Click **New repository secret**
4. Add each secret:
   - Name: `DOCKERHUB_USERNAME`, Value: `your-dockerhub-username`
   - Name: `DOCKERHUB_TOKEN`, Value: `your-dockerhub-token`

### Security Best Practices

- ✅ Never commit secrets to the repository
- ✅ Use tokens with minimal required permissions
- ✅ Rotate tokens regularly
- ✅ Use different tokens for different environments
- ✅ Monitor token usage

---

## 🛡️ Security Features

### Shift-Left Security
Security checks are performed early in the pipeline (stages 3-5) to catch issues when they're cheapest to fix.

### Multi-Layered Security
1. **Code Level:** SAST (CodeQL) scans source code
2. **Dependency Level:** SCA (OWASP Dependency-Check) scans libraries
3. **Container Level:** Trivy scans container image
4. **Runtime Level:** Smoke tests validate container behavior

### Fail-Fast Behavior
Pipeline stops immediately on:
- Code quality violations
- Security vulnerabilities (high/critical)
- Test failures
- Build errors
- Container scan failures

### Quality Gates
Each stage acts as a quality gate:
- ❌ No stage can be skipped
- ❌ Failures stop the pipeline
- ✅ Only fully validated code reaches production

---

## 📊 Pipeline Artifacts

The pipeline generates the following artifacts:

1. **Checkstyle Results** - Code quality report
2. **Dependency-Check Report** - Dependency vulnerability analysis
3. **Test Results** - Unit test execution results
4. **Build Artifact** - Compiled JAR file
5. **Trivy Scan Report** - Container security scan results

All artifacts are retained for 30 days (7 days for build artifacts) and can be downloaded from the GitHub Actions UI.

---

## 🧪 Running Security Scans Locally

### Checkstyle (Code Quality)
```bash
mvn checkstyle:check
```

### OWASP Dependency-Check (SCA)
```bash
mvn org.owasp:dependency-check-maven:check
```
View report: `target/dependency-check-report.html`

### Trivy (Container Scan)
```bash
# Build image first
docker build -t chefapp:test .

# Scan image
trivy image chefapp:test
```

### CodeQL (SAST)
CodeQL analysis runs automatically in GitHub Actions. For local analysis, use the CodeQL CLI.

---

## 📁 Project Structure

```
chefapp/
├── .github/
│   └── workflows/
│       └── ci.yml                 # Main CI/CD pipeline
├── src/
│   ├── main/
│   │   ├── java/                  # Application source code
│   │   ├── resources/             # Configuration files
│   │   └── webapp/                # JSP views
│   └── test/                      # Unit tests
├── Dockerfile                     # Production container definition
├── .dockerignore                  # Docker build exclusions
├── pom.xml                        # Maven configuration with security plugins
├── checkstyle.xml                 # Code quality rules
├── dependency-check-suppressions.xml  # OWASP suppressions
└── README.md                      # This file
```

---

## 🔍 API Endpoints

### Chef Endpoints
- `GET /chefs` - List all chefs
- `GET /chefs/new` - Show form for new chef
- `POST /chefs` - Create new chef
- `GET /chefs/{id}/edit` - Show form for editing chef
- `POST /chefs/{id}` - Update chef
- `GET /chefs/specialization/{specialization}` - Filter by specialization
- `GET /chefs/category/{category}` - Filter by dish category

### Dish Endpoints
- `GET /dishes` - List all dishes
- `GET /dishes/new` - Show form for new dish
- `POST /dishes` - Create new dish
- `GET /dishes/{id}/edit` - Show form for editing dish
- `POST /dishes/{id}` - Update dish

---

## 🧪 Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=ChefServiceTest
```

### Test Coverage
Test coverage reports are generated in `target/site/jacoco/` (if Jacoco plugin is configured).

---

## 🐳 Docker Details

### Image Security Features
- **Multi-stage build** - Reduces final image size and attack surface
- **Non-root user** - Container runs as `chefapp` user (not root)
- **Minimal base image** - Uses `eclipse-temurin:17-jre-jammy` (only JRE, not JDK)
- **Health checks** - Built-in health check endpoint
- **Security updates** - Base image updated during build
- **No secrets** - No hardcoded credentials in image

### Image Layers
1. **Base Layer:** Eclipse Temurin JRE 17
2. **Application Layer:** Compiled JAR file
3. **Configuration Layer:** User and permissions setup

### Container Resources
- **Memory:** Configured for container environments (`-XX:MaxRAMPercentage=75.0`)
- **CPU:** Uses container-aware JVM settings
- **Port:** Exposes port 8080

---

## 📈 Pipeline Metrics

### Typical Pipeline Duration
- **Full Pipeline:** ~8-12 minutes
- **With Cache Hits:** ~5-8 minutes

### Stage Durations (Approximate)
- Checkout: ~10 seconds
- Setup: ~30 seconds
- Linting: ~30 seconds
- SAST: ~3-5 minutes
- SCA: ~2-3 minutes
- Unit Tests: ~1-2 minutes
- Build: ~1-2 minutes
- Docker Build: ~2-3 minutes
- Image Scan: ~1-2 minutes
- Smoke Test: ~30 seconds
- Docker Push: ~30 seconds

---

## 🚨 Troubleshooting

### Pipeline Fails on Checkstyle
- Review `target/checkstyle-result.xml` for violations
- Fix code style issues
- Re-run pipeline

### Pipeline Fails on Dependency-Check
- Review `target/dependency-check-report.html`
- Update vulnerable dependencies
- Add suppressions for false positives in `dependency-check-suppressions.xml`

### Pipeline Fails on Trivy Scan
- Review Trivy scan report
- Update base image if vulnerabilities found
- Review installed packages

### Container Smoke Test Fails
- Check container logs: `docker logs chefapp-test`
- Verify application configuration
- Check health check endpoint

### Docker Push Fails
- Verify `DOCKERHUB_USERNAME` and `DOCKERHUB_TOKEN` secrets are set
- Check token has write permissions
- Verify Docker Hub account is active

---

## 📚 Additional Resources

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [CodeQL Documentation](https://codeql.github.com/docs/)
- [OWASP Dependency-Check](https://owasp.org/www-project-dependency-check/)
- [Trivy Documentation](https://aquasecurity.github.io/trivy/)
- [Docker Best Practices](https://docs.docker.com/develop/dev-best-practices/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

---

## 🎓 Learning Outcomes

This project demonstrates:

1. **DevSecOps Principles:** Security integrated throughout the development lifecycle
2. **Shift-Left Security:** Early detection and prevention of security issues
3. **Quality Gates:** Automated enforcement of code quality and security standards
4. **Container Security:** Multi-layered security for containerized applications
5. **CI/CD Best Practices:** Production-grade pipeline design and implementation
6. **Fail-Fast Philosophy:** Immediate feedback on issues
7. **Security Tooling:** Integration of industry-standard security tools

---

## 📝 License

This project is for educational purposes as part of an academic DevOps course.

---

## 👥 Contributing

When contributing to this project:

1. Ensure all security scans pass locally
2. Run tests before committing
3. Follow code style guidelines (Checkstyle)
4. Update documentation as needed
5. Ensure pipeline passes before merging

---

**Built with security, quality, and reliability in mind.** 🔒✨