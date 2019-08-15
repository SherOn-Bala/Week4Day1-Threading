## Threading ##

### Coding ###
1. Use Java Thread/Runnables to calculate pi to 12 digits.
2. Use AsyncTask to sort an array of 1000 randomly generated numbers.
3. Use a looper to calculate the Fibonacci sequence unto the 20th iteration.
4. Create a small Sqlite database and use a loader to perform any crud operations from the database (You can have as many fields as you want, just make sure to use the loaders for any crud operations).

### Research ###
1. <b>What are loaders and how do we implement loaders?</b>
* What?
    * lets you load data from a content provider or other data source to display.
    * runs on separate threads to prevent unresponsive UI.
    * simplifies thread management by providing callback methods when events occur.
    * persist and cache results across configuration changes to prevent duplicate queries.
    * can implement an observer to monitor for changes in the underlying data source.
* How?
    * LoaderManager used to initialize loaders in acitivities/fragments in onCreate()/onStart().
    * init() and restartLoader() needs:
        * LOADER_ID: Uniq ID given to Loader, if we are initializing Loader, will check if the Loader ID is present or not. It will call on the callbacks method accordingly, which I wil explain later.
        * OPTION_ARGUMENT: It is the argument given to Loader at the time of initialization.
        * CALLBACK_CLASS_REFERENCE: Class that implements all it's callback function, which Loader will return data.
    * LoaderManager.LoaderCallBacks provides 3 methods called sequentially:
        * OnCreateLoader(int,bundle): We will call on this method when we initialize Loader. In can only be called when no Loader with LOADER_ID is already present. onCreateLoader() will return the Loader object, either CursorLoader, AyncTaskLoader or CustomImplementedLoader.
        * OnLoadFinished(): Called after our data loading is finished. Basically we'll do all the user interface binding and other things here.
        * OnLoaderReset(): Every time our Loader gets reset (either activity/ Fragment get destroyed), we have to clear all the refereces from current Loader in this callback.
    * Loader, an abstract class, which Android has 2 implementations of:
        * AsyncTaskLoader: A Loader that provides AsyncTask to perform background operations.
        * CusorLoader: A Loader implementation used to get data from content providers through content resolvers.
2. <b>What is an AsyncTaskLoader?</b>
* A class that implements the Loader class and loads data in the background and reassociates background tasks with the activity, even after configuration change.
* Changing orientation in an activity still yields same results for the UI.
3. <b>What is a Handler Thread for?</b>
* A Handler allows you to communicate back with the UI thread from a background thread.
* Allows you to send and process Message and Runnable objects asscoiated with a thread's MessageQueue.
4. <b>What are some common threading restrictions in android?</b>
* Cannot update the UI directly from a background thread.
* Cannot run long running processes on the UI thread, such as pulling data from the internet.
5. <b>What are thread pools and thread pool executors?</b>
* A thread pool is a single FIFO task queue with a group of worker threads.
* The producers, such as the UI thread, sends tasks to the task queue.
* Whenever worker threads in the thread pool become available, they remove the taks from the front of the queue and start running them.
* ThreadPoolExecuter allows you to specify how many core threads and max threads the pool should create and the keep alive time for the idle threads.
