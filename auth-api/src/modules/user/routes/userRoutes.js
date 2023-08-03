import { Router } from "express";

import userController from "../controller/userController.js";
import checkToken from "../../../config/auth/checkToken.js";

const router = new Router();

router.get("/api/user/email/:email", userController.findByEmail);

router.use(checkToken);

router.post("/api/user/auth", userController.getAccessToken);

export default router;