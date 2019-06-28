# Kotlin Multiplatform example project using TMDb

This is a Kotlin [Multiplatform project](https://kotlinlang.org/docs/reference/multiplatform.html) trying to share as much code as possible between Android and iOS, using the API from [TMDb](https://www.themoviedb.org/) and displays a list of movies retrieved from it.

In particular, this project uses a Clean Architecture approach, sharing the complete `data` and `domain` layer, as also the `presenter` from the `presentation` layer. Each platform currently only implements the `View` interface and adds a native UI to visualize the data from the `presenter`.

Read the full story [here](https://proandroiddev.com/clean-architecture-example-with-kotlin-multiplatform-c361bb283fd0). 
