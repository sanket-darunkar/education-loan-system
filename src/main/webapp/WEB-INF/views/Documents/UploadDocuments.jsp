<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Documents</title>

    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 40%;
            margin: 50px auto;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 6px;
        }
        label {
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin: 8px 0 15px 0;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #2e86de;
            color: white;
            border: none;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #1b4f72;
        }
    </style>
</head>

<body>

<div class="container">
    <h2 style="text-align:center;">📤 Upload Documents</h2>

    <form action="/documents/upload" method="post" enctype="multipart/form-data">

        <!-- Document Type -->
        <label>Document Type</label>
        <select name="documentType" required>
            <option value="">-- Select Document --</option>
            <option value="AADHAR">Aadhar Card</option>
            <option value="PAN">PAN Card</option>
            <option value="MARKSHEET">Marksheet</option>
            <option value="INCOME_CERT">Income Certificate</option>
        </select>

        <!-- Upload File -->
        <label>Choose File</label>
        <input type="file" name="file" required />

        <!-- Submit -->
        <button type="submit">Upload</button>
    </form>
</div>

</body>
</html>
