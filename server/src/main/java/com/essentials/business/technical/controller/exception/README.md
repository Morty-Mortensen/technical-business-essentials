# Adding a new Exception.

1. Update ErrorType enum (if new error status code is needed).
2. Update /utils/ExceptionHandler to add new enum to switch/case operation (if step 1 was needed).
3. Update /controller/exception/controller/ErrorHandlerController to return ErrorInfo object and appropriate status code.
