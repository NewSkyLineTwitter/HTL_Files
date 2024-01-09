import express, { Request, Response } from 'express';
import products from './routes/products';

const app = express();

app.use(express.json());

app.use("/products", products);

app.get("/");

app.listen(3000, () => {
    console.log("Server is running on port 3000");
});

export default app;