## Development Guideline

### To create development environment in local

- Checkout the Git repo, and run `npm i` to set up Git hooks.
  - If you're using `nvm`, it's suggested to [automate `nvm use`](https://dev.to/d4nyll/automatic-nvm-use-34ol).

### Before you submit pull request

- To format Java codes, run `./gradlew spotlessApply` that is supported by [spotless plugin](https://github.com/diffplug/spotless). Basically you don't need to run them manually, because the `pre-commit` hook runs it automatic.
- Run `./gradlew` to make sure the change passes compilation and automated checking.
