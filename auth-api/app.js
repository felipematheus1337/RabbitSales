import express from "express";
import * as db from "./src/config/db/initialData.js";
import userRoutes from "./src/modules/user/routes/userRoutes.js";
import checkToken from "./src/config/auth/checkToken.js";

const app = express();
const env = process.env;
const PORT = env.PORT || 8080;

app.get("/api/status",  (req,res) => {
    return res.status(200).json({
        service: 'Auth-API',
        status: 'up',
        httpStatus: 200
    })
})

db.createInitialData();

app.use(userRoutes);
app.use(express.json());

app.use(checkToken);

app.listen(PORT, () => {
    console.info(`Server started sucessfully at port ${PORT}`);
})

