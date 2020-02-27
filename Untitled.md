## buildkite

#### 1. Pipeline 

##### 1.1 steps 的四种类型

 [command steps](https://buildkite.com/docs/pipelines/command-step), [wait steps](https://buildkite.com/docs/pipelines/wait-step), [block steps](https://buildkite.com/docs/pipelines/block-step), and [trigger steps](https://buildkite.com/docs/pipelines/trigger-step).

Customizing the pipeline upload path

#### 2.Writing Build Scripts

##### 2.1 Configuring Bash

| `e`          | Exit script immediately if any command returns a non-zero exit status. |
| :----------- | ------------------------------------------------------------ |
| `u`          | Exit script immediately if an undefined variable is used (e.g. `echo "$UNDEFINED_ENV_VAR"`). |
| `o pipefail` | Ensure Bash pipelines (e.g. `cmd | othercmd`) return a non-zero status if any of the commands fail, rather than returning the exit status of the last command in the pipeline. |
| `x`          | Expand and print each command before executing. See [Debugging your environment](https://buildkite.com/docs/builds/writing-build-scripts#debugging-your-environment) for more information. |

##### 2.2 Capturing exit status

```
#!/bin/bash

# Note that we don't enable the 'e' option, which would cause the script to
# immediately exit if 'run_tests' failed
set -uo pipefail

# Run the main command we're most interested in
run_tests

# Capture the exit status
TESTS_EXIT_STATUS=$?

# Run additional commands
clean_up

# Exit with the status of the original command
exit $TESTS_EXIT_STATUS
```

#### 2. agents

##### 2.1  Configuration

##### 2.2  Hooks

##### 2.3  Queues

##### 2.4 Buildkite Agent Prioritization

##### 2.5 Agent Tokens