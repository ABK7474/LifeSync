import os
import re

base_path = r"D:\LifeSync\src\com\lifesync"

imports = """
import com.lifesync.model.*;
import com.lifesync.service.*;
import com.lifesync.repository.*;
import com.lifesync.factory.*;
import com.lifesync.exception.*;
import com.lifesync.interfaces.*;
import com.lifesync.util.*;
"""

main_imports = imports + "import com.lifesync.gui.*;\n"

for root, _, files in os.walk(base_path):
    for file in files:
        if file.endswith(".java"):
            file_path = os.path.join(root, file)
            
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # Determine expected package
            rel_path = os.path.relpath(root, base_path)
            if rel_path == '.':
                expected_package = "com.lifesync"
            else:
                sub_path = rel_path.replace(os.sep, '.')
                expected_package = f"com.lifesync.{sub_path}"
            
            # Replace package declaration
            content = re.sub(r'package\s+com\.lifesync(?:\.[a-z]+)*\s*;', f"package {expected_package};", content)
            
            # Remove existing com.lifesync wildcard imports to avoid duplication
            content = re.sub(r'(?m)^import com\.lifesync\..*\*;.*\n?', '', content)
            
            # Insert imports
            ins_imports = main_imports if file == "Main.java" else imports
            content = re.sub(f"(package {expected_package};)", f"\\1\n{ins_imports}", content)
            
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(content)

print("Updated packages and imports.")
