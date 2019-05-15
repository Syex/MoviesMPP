import UIKit
import main

/// Implements the shared Kotlin interface (now a protocol) and besides only renders the movies.
/// Doesn't show any loading indicators as movies are loaded from network on the main thread.
class MoviesViewController: UICollectionViewController, PopularMoviesView {
    
    private lazy var presenter = ServiceLocator.init().popularMoviesPresenter
    
    private var movies = [Movie]()
    private let moviesPerRow = 2
    /// Insets for each movie cell
    private let sectionInsets = UIEdgeInsets(top: 30.0,
                                             left: 20.0,
                                             bottom: 0.0,
                                             right: 20.0)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.attachView(view: self)
    }

    func setPopularMovies(movies: [Movie]) {
        self.movies = movies
        collectionView?.reloadData()
    }
    
    func showMoviesFailedToLoad() {
        print("Movies failed to load")
    }
    
    func setLoadingVisible(visible: Bool) {
        print("Setting loading visible \(visible)")
    }
}

// MARK: - UICollectionViewDataSource
extension MoviesViewController {
    
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        return movies.count
    }
    
    override func collectionView(_ collectionView: UICollectionView,
                                 numberOfItemsInSection section: Int) -> Int {
        return moviesPerRow
    }
    
    override func collectionView(
        _ collectionView: UICollectionView,
        cellForItemAt indexPath: IndexPath
        ) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "MovieCell",
                                                      for: indexPath) as! MovieCell
        
        let index = (indexPath.section * moviesPerRow) + indexPath.row
        if (index < movies.count) {
            let movie = movies[index]
            cell.titleView.text = movie.title
            cell.loadPoster(url: movie.completePosterPath)
        }
        
        return cell
    }
}

// MARK: - Collection View Flow Layout Delegate
extension MoviesViewController : UICollectionViewDelegateFlowLayout {

    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        sizeForItemAt indexPath: IndexPath) -> CGSize {
        let padding = Int(sectionInsets.left) * (moviesPerRow + 1)
        let availableWidth = Int(view.frame.width) - padding
        let widthPerItem = availableWidth / moviesPerRow
        
        return CGSize(width: widthPerItem, height: 250)
    }
    
    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        insetForSectionAt section: Int) -> UIEdgeInsets {
        return sectionInsets
    }
    
    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return sectionInsets.left
    }
}
