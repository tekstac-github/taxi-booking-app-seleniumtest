from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options
import time

# Setup WebDriver with headless options
chrome_options = Options()
chrome_options.add_argument("--headless")
chrome_options.add_argument("--no-sandbox")
chrome_options.add_argument("--disable-dev-shm-usage")
chrome_options.add_argument("--remote-debugging-port=9222")

# Make sure the right driver is installed (like chromedriver)
driver = webdriver.Chrome(options=chrome_options)

try:
    # Open the taxi booking app
    driver.get("http://localhost:9090/taxiapp/book")

    # Find and fill the pickup location
    pickup_input = driver.find_element(By.NAME, "pickup")
    pickup_input.send_keys("Downtown")

    # Find and fill the destination location
    destination_input = driver.find_element(By.NAME, "destination")
    destination_input.send_keys("Airport")

    # Submit the form
    submit_button = driver.find_element(By.XPATH, "//button[text()='Book Ride']")
    submit_button.click()

    # Optional: wait for response
    time.sleep(2)

    # Verify submission (adjust selector based on app behavior)
    confirmation_message = driver.find_element(By.ID, "confirmation")
    assert "Booking confirmed" in confirmation_message.text

    print("Test passed: Ride successfully booked")

except Exception as e:
    print(f"Test failed: {e}")

finally:
    driver.quit()
