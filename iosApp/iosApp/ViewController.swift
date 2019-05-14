import UIKit
import main

class ViewController: UIViewController, PopularMoviesView {
    
    @IBOutlet weak var label: UILabel!
    
    private lazy var presenter = ServiceLocator.init().popularMoviesPresenter
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.attachView(view: self)
    }

    func setPopularMovies(movies: [Movie]) {
        print(movies)
    }
    
    func showMoviesFailedToLoad() {
        print("Movies failed to load")
    }
    
    func setLoadingVisible(visible: Bool) {
        print("Setting loading visible \(visible)")
    }
    
}
