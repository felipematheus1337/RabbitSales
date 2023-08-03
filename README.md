
RabbitSales

RabbitSales is a comprehensive platform for sales and inventory management, developed using Node.js, Spring, and RabbitMQ. This solution consists of three interconnected APIs that work together to provide a seamless and secure experience for managing product sales.

Features
Sales Registration API (Node.js): This API is responsible for receiving and registering all sales made on the platform. Users can submit sales details, including purchased products, quantities, prices, and customer information. Leveraging the integration with RabbitMQ, sales information is efficiently processed and stored.

Inventory Control API (Spring): This API handles the product inventory of the company. It maintains real-time control of available quantities, updating them as sales are registered, and ensuring the inventory remains synchronized to prevent overselling.

Authentication API (Node.js): Security is a top priority, and this API provides user authentication and management services. It utilizes advanced authentication mechanisms to protect data and ensures that only authorized users can perform sales and access relevant information.

Getting Started
Follow these steps to get the RabbitSales project up and running:

Clone the repository: git clone https://github.com/felipematheus1337/RabbitSales.git


## Contributing
We welcome contributions to enhance the RabbitSales project. If you find any issues or have suggestions for improvements, please feel free to submit a pull request.

## License
This project is licensed under the MIT License.

## Contact
If you have any questions or need further assistance, you can reach out to the project maintainers at lipehbr@gmail.com

Thank you for choosing RabbitSales! We hope it serves as a robust and scalable solution for your sales and inventory management needs. Happy coding!