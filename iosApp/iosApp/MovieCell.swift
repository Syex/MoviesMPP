import UIKit

/// Custom cell displaying a movie poster and its title.
class MovieCell: UICollectionViewCell {
    @IBOutlet weak var posterView: UIImageView!
    
    @IBOutlet weak var titleView: UILabel!
    
    func loadPoster(url: String) {
        posterView.loadImageFromURL(urlString: url)
    }
}

extension UIImageView {
    
    /// Function taken from https://stackoverflow.com/a/37019507/1477936 to load an image from a URL.
    func loadImageFromURL(urlString: String) {
        self.image = nil
        URLSession.shared.dataTask(with: NSURL(string: urlString)! as URL, completionHandler: { (data, response, error) -> Void in
            
            if (error != nil) {
                print(error!)
                return
            }
            
            DispatchQueue.main.async(execute: { () -> Void in
                let image = UIImage(data: data!)
                self.image = image
            })
            
        }).resume()
    }}
