
# > test-only testingscala.tags.TagTest -- -n SlowTest
[info] TagTest:
[info] built-in tags
[info] custom tags allow me to select which tests to run
[info] - will get run when choosing to run slow tests
[info] ScalaTest
[info] Run completed in 4 seconds, 257 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[info] Passed: Total 1, Failed 0, Errors 0, Passed 1
[success] Total time: 5 s, completed Jun 29, 2016 11:00:15 PM

# > test-only testingscala.tags.TagTest -- -n FastTest
[info] TagTest:
[info] built-in tags
[info] custom tags allow me to select which tests to run
[info] - will run pretty fast
[info] - will run much faster
[info] ScalaTest
[info] Run completed in 4 seconds, 274 milliseconds.
[info] Total number of tests run: 2
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 2, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[info] Passed: Total 2, Failed 0, Errors 0, Passed 2
[success] Total time: 4 s, completed Jun 29, 2016 11:00:28 PM

# > test-only testingscala.tags.TagTest
[info] TagTest:
[info] built-in tags
[info] - will ignore this because I replaced 'it' with 'ignore' !!! IGNORED !!!
[info] custom tags allow me to select which tests to run
[info] - will get run when choosing to run slow tests
[info] - will run pretty fast
[info] - will run much faster
[info] ScalaTest
[info] Run completed in 4 seconds, 266 milliseconds.
[info] Total number of tests run: 3
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 3, failed 0, canceled 0, ignored 1, pending 0
[info] All tests passed.
[info] Passed: Total 3, Failed 0, Errors 0, Passed 3, Ignored 1
[success] Total time: 4 s, completed Jun 29, 2016 11:00:54 PM
