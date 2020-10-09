## Question

1. What is a brew tap?

**brew tap** adds more repositories to the list of formulae that **brew** tracks, updates, and installs from. By default, **tap** assumes that the repositories come from GitHub, but the command isn't limited to any one location.

2. test framework

   1. unit test

      - go test
      - gomega
      - fakeclient
      - envtest

   2. Running the end-to-end tests

      - gomega

      The end-to-end tests are meant to verify the proper functioning of a Cluster API management cluster in an environment that resemble a real production environment.

      Following guidelines should be followed when developing E2E tests:

      - Use the [Cluster API test framework].
      - Define test spec reflecting real user workflow, e.g. [Cluster API quick start].
      - Unless you are testing provider specific features, ensure your test can run with different infrastructure providers (see [Writing Portable Tests](https://cluster-api.sigs.k8s.io/developer/testing.html#writing-portable-e2e-tests)).

      