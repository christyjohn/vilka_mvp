-- Step 1: Add status column (nullable first)
ALTER TABLE vendors
ADD COLUMN status VARCHAR(50);

-- Step 2: Backfill existing rows
-- Map old is_active → new status
UPDATE vendors
SET status = CASE
    WHEN is_active = TRUE THEN 'APPROVED'
    ELSE 'REJECTED'
END
WHERE status IS NULL;

-- Step 3: Enforce NOT NULL + default
ALTER TABLE vendors
MODIFY status VARCHAR(50) NOT NULL DEFAULT 'PENDING';

-- Step 4: Drop old column
ALTER TABLE vendors
DROP COLUMN is_active;

-- Optional: index for faster queries
CREATE INDEX idx_vendors_status ON vendors(status);

ALTER TABLE vendors
ADD CONSTRAINT chk_vendor_status
CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED'));