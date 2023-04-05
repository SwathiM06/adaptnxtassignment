import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ProductScraper {
    public static void main(String[] args) {
        // URL of the website to scrape
        String url = "https://www.staples.com/Computer-office-desks/cat_CL210795/663ea?icid=BTS:2020:STUDYSPACE:DESKS";
        
        try {
            // Create a CSV file
            FileWriter csvWriter = new FileWriter("products.csv");
            
            // Write headers to the CSV file
            csvWriter.append("Product Name,Product Price,Item Number,Model Number,Product Category,Product Description\n");
            
            // Fetch the website content using Jsoup
            Document document = Jsoup.connect(url).get();
            
            // Get the product items
            Elements productItems = document.select(".card__details");
            
            // Loop through the product items and extract the required details
            for (int i = 0; i < 10; i++) {
                // Get the product name
                String productName = productItems.get(i).select(".card__title").text();
                
                // Get the product price
                String productPrice = productItems.get(i).select(".card__price").text();
                
                // Get the item number
                String itemNumber = productItems.get(i).select(".card__sku").text();
                
                // Get the model number
                String modelNumber = productItems.get(i).select(".card__model").text();
                
                // Get the product category
                String productCategory = productItems.get(i).select(".card__category").text();
                
                // Get the product description
                String productDescription = productItems.get(i).select(".card__desc").text();
                
                // Write the product details to the CSV file
                csvWriter.append(String.join(",", productName, productPrice, itemNumber, modelNumber, productCategory, productDescription));
                csvWriter.append("\n");
            }
            
            // Close the CSV file
            csvWriter.flush();
            csvWriter.close();
            
            System.out.println("Product details saved to products.csv");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}